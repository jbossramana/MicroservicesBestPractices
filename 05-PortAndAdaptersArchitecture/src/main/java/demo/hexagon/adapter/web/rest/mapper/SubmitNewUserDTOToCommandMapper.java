package demo.hexagon.adapter.web.rest.mapper;

import hu.dev.hexagon.rest.model.SubmitNewUserDTO;

import java.util.function.Function;

import demo.hexagon.application.port.commands.SubmitNewUserCommand;

public class SubmitNewUserDTOToCommandMapper implements Function<SubmitNewUserDTO, SubmitNewUserCommand> {

    @Override
    public SubmitNewUserCommand apply(final SubmitNewUserDTO submitNewUserDTO) {
        return new SubmitNewUserCommand.SubmitNewUserCommandBuilder()
                .userName(submitNewUserDTO.getUserName())
                .firstName(submitNewUserDTO.getFirstName())
                .lastName(submitNewUserDTO.getLastName())
                .age(submitNewUserDTO.getAge())
                .build();
    }
}
