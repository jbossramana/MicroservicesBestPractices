package demo.hexagon.adapter.web.rest;

import hu.dev.hexagon.rest.api.UserApi;
import hu.dev.hexagon.rest.model.ModifyUserDTO;
import hu.dev.hexagon.rest.model.SubmitNewUserDTO;
import hu.dev.hexagon.rest.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.hexagon.adapter.web.rest.mapper.ModifyUserDTOToCommandMapper;
import demo.hexagon.adapter.web.rest.mapper.SubmitNewUserDTOToCommandMapper;
import demo.hexagon.adapter.web.rest.mapper.UserDomainToUserDTOMapper;
import demo.hexagon.application.port.DeleteUser;
import demo.hexagon.application.port.DisplayUser;
import demo.hexagon.application.port.ModifyUser;
import demo.hexagon.application.port.SubmitNewUser;
import demo.hexagon.application.port.commands.ModifyUserCommand;
import demo.hexagon.application.port.commands.SubmitNewUserCommand;
import demo.hexagon.domain.model.User;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = ApiBasePath.API_V1)
class UserRestApiAdapter implements UserApi {

    private final SubmitNewUser submitNewUser;
    private final DisplayUser displayUser;
    private final ModifyUser modifyUser;
    private final DeleteUser deleteUser;

    private final Function<SubmitNewUserDTO, SubmitNewUserCommand> TO_SUBMIT_COMMAND = new SubmitNewUserDTOToCommandMapper();
    private final Function<ModifyUserDTO, ModifyUserCommand> TO_MODIFY_COMMAND = new ModifyUserDTOToCommandMapper();
    private final Function<User, UserDTO> TO_USER_DTO = new UserDomainToUserDTOMapper();

    @Autowired
    public UserRestApiAdapter(final SubmitNewUser submitNewUser, final DisplayUser displayUser,
                              final ModifyUser modifyUser, final DeleteUser deleteUser) {
        this.submitNewUser = submitNewUser;
        this.displayUser = displayUser;
        this.modifyUser = modifyUser;
        this.deleteUser = deleteUser;
    }

    @Override
    public ResponseEntity<Void> deleteUser(final String userName) {
        deleteUser.deleteByUserName(userName);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserDTO> getUserByUserName(final String userName) {
        final User user = displayUser.findByUserName(userName);
        return ResponseEntity.ok().body(TO_USER_DTO.apply(user));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        final List<UserDTO> users = displayUser.listAll()
                .stream()
                .map(TO_USER_DTO)
                .toList();
        return ResponseEntity.ok().body(users);
    }

    @Override
    public ResponseEntity<UserDTO> submitUser(final SubmitNewUserDTO submitNewUserRequest) {
        final SubmitNewUserCommand submitNewUserCommand = TO_SUBMIT_COMMAND.apply(submitNewUserRequest);
        final User submittedUser = submitNewUser.submit(submitNewUserCommand);
        return new ResponseEntity<>(TO_USER_DTO.apply(submittedUser), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> modifyUser(final String userName, final ModifyUserDTO modifyUserDTO) {
        final ModifyUserCommand modifyUserCommand = TO_MODIFY_COMMAND.apply(modifyUserDTO);
        final User modifiedUser = modifyUser.modify(userName, modifyUserCommand);
        return ResponseEntity.ok().body(TO_USER_DTO.apply(modifiedUser));
    }

}
