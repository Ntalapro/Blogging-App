package com.bloggingapp.bloggingapp.users;


import com.bloggingapp.bloggingapp.users.dtos.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Create a new user
    public UserEntity createUser(CreateUserRequest u){
        var newUser = UserEntity.builder()
                .username(u.getUsername())
   //             .password(password) TODO: encrypt password
                .email(u.getEmail())
                .build();
        return usersRepository.save(newUser);

    }

    // Get user by username
    public  UserEntity getUser(String username){
        return usersRepository.findByUsername(username).orElseThrow();
    }

    public  UserEntity getUser(Long id){
        return usersRepository.findById(id).orElseThrow();
    }


    public UserEntity loginUser(String username, String password){
        var user = usersRepository.findByUsername(username).orElseThrow();
        //TODO: match password
        return user;
    }


//    static class UserNotFoundException extends IllegalArgumentException{
//        public UserNotFoundException(Long id){
//            super("User with Id: "+id+" not found ");
//        }
//
//        public UserNotFoundException(String username){
//            super("User with username: "+username+" not found ");
//        }
//
//    }

}
