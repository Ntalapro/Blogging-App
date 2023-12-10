package com.bloggingapp.bloggingapp.users;


import com.bloggingapp.bloggingapp.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Create a new user
    public UserEntity createUser(CreateUserRequest u){
        UserEntity newUser = modelMapper.map(u,UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return usersRepository.save(newUser);

    }

    // Get user by username
    public  UserEntity getUser(String username){
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public  UserEntity getUser(Long id){
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


    public UserEntity loginUser(String username, String password) throws InvalidCredentialsException {
        UserEntity user =  usersRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
        boolean passMatch = passwordEncoder.matches(password,user.getPassword());
        if(!passMatch) throw new InvalidCredentialsException();
        return user;
    }



    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(Long id){
            super("User with Id: "+id+" not found ");
        }

        public UserNotFoundException(String username){
            super("User with username: "+username+" not found ");
        }

    }

    public static class InvalidCredentialsException extends IllegalAccessException{
         public InvalidCredentialsException(){
             super("Invalid credentials!");
         }

    }

}
