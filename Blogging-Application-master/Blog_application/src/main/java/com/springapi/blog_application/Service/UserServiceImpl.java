package com.springapi.blog_application.Service;

import com.springapi.blog_application.Exception.ResourceNotFoundException;
import com.springapi.blog_application.Model.User;
import com.springapi.blog_application.Payloads.UserDto;
import com.springapi.blog_application.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userdto) {
        User user = this.dtoToUser(userdto);
        User  saveUser = userRepo.save(user);
        return this.userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userdto, Integer userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","id",userId) );

        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setAbout(userdto.getAbout());

        User updatedUser = userRepo.save(user);
        UserDto updatedUserDto = this.userToDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);


//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
