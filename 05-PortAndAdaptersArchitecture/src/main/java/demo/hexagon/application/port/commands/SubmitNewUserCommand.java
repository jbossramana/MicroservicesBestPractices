package demo.hexagon.application.port.commands;


public class SubmitNewUserCommand {
    private String userName;
    private String firstName;
    private String lastName;
    private int age;

    private SubmitNewUserCommand(final SubmitNewUserCommand.SubmitNewUserCommandBuilder submitNewUserCommandBuilder) {
        this.userName = submitNewUserCommandBuilder.userName;
        this.firstName = submitNewUserCommandBuilder.firstName;
        this.lastName = submitNewUserCommandBuilder.lastName;
        this.age = submitNewUserCommandBuilder.age;
    }

    public String getUserName() {
        return userName;
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

    public static class SubmitNewUserCommandBuilder {
        private String userName;
        private String firstName;
        private String lastName;
        private int age;

        public SubmitNewUserCommand build() {
            return new SubmitNewUserCommand(this);
        }

        public SubmitNewUserCommand.SubmitNewUserCommandBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public SubmitNewUserCommand.SubmitNewUserCommandBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public SubmitNewUserCommand.SubmitNewUserCommandBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public SubmitNewUserCommand.SubmitNewUserCommandBuilder age(final int age) {
            this.age = age;
            return this;
        }
    }
}
