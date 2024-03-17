package demo.hexagon.adapter.web.rest.mapper;

import hu.dev.hexagon.rest.model.ModifyUserDTO;

import java.util.function.Function;

import demo.hexagon.application.port.commands.ModifyUserCommand;

public class ModifyUserDTOToCommandMapper implements Function<ModifyUserDTO, ModifyUserCommand> {

    @Override
    public ModifyUserCommand apply(final ModifyUserDTO modifyUserDTO) {
        return new ModifyUserCommand.ModifyUserCommandBuilder()
                .firstName(modifyUserDTO.getFirstName())
                .lastName(modifyUserDTO.getLastName())
                .age(modifyUserDTO.getAge())
                .build();
    }
}
