package demo.hexagon.application.port.commands;

public class ModifyUserCommand {
    private String firstName;
    private String lastName;
    private int age;

    private ModifyUserCommand(final ModifyUserCommand.ModifyUserCommandBuilder modifyUserCommandBuilder) {
        this.firstName = modifyUserCommandBuilder.firstName;
        this.lastName = modifyUserCommandBuilder.lastName;
        this.age = modifyUserCommandBuilder.age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static class ModifyUserCommandBuilder {
        private String firstName;
        private String lastName;
        private int age;

        public ModifyUserCommand build() {
            return new ModifyUserCommand(this);
        }

        public ModifyUserCommand.ModifyUserCommandBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ModifyUserCommand.ModifyUserCommandBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ModifyUserCommand.ModifyUserCommandBuilder age(final int age) {
            this.age = age;
            return this;
        }
    }
}
