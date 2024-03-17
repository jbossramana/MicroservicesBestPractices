package demo.hexagon.application.port;

import demo.hexagon.application.port.commands.ModifyUserCommand;
import demo.hexagon.domain.model.User;

public interface ModifyUser {
    User modify(String userName, ModifyUserCommand modifyUserCommand);
}
