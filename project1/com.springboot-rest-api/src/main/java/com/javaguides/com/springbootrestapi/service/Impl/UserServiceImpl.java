package com.javaguides.com.springbootrestapi.service.Impl;

import com.javaguides.com.springbootrestapi.dto.UserDto;
import com.javaguides.com.springbootrestapi.entity.User;
import com.javaguides.com.springbootrestapi.exception.EmailAlreadyExistException;
import com.javaguides.com.springbootrestapi.exception.ResourceNotFoundException;
import com.javaguides.com.springbootrestapi.repository.UserRepository;
import com.javaguides.com.springbootrestapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        
        this.userRepository = userRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User>optionalUser=userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
           throw new EmailAlreadyExistException("Email Already Exist for User") ;

        }
        User user=mapToEntity(userDto);
       User userSaved=  userRepository.save(user);
       UserDto newUserDto=mapToDto(userSaved);
        return newUserDto;
    }

    private UserDto mapToDto(User userSaved) {
        UserDto userDto=modelMapper.map(userSaved,UserDto.class);
//        UserDto userDto=new UserDto();
//        userDto.setId(userSaved.getId());
//        userDto.setFirstName(userSaved.getFirstName());
//        userDto.setLastName(userSaved.getLastName());
//        userDto.setEmail(userSaved.getEmail());
        return userDto;
    }


    private User mapToEntity(UserDto userDto) {
        User user=modelMapper.map(userDto,User.class);
//        User user=new User();
//        user.setFirstName(userDto.getFirstName());
//        user.setLastname(userDto.getLastName());
//        user.setEmail(userDto.getEmail());
        return user;
    }

    @Override
    public UserDto getUserByid(long id) {
        User user=userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",id)
        );
       // User user= optionalUser.get();
        return mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User>users=userRepository.findAll();
       return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(long id, UserDto user) {
      User existingUser=  userRepository.findById(id).orElseThrow(
              ()->new ResourceNotFoundException("User","id",id)
      );

      existingUser.setFirstName(user.getFirstName());
      existingUser.setLastname(user.getLastName());
      existingUser.setEmail(user.getEmail());

      User updatedUser=userRepository.save(existingUser);
        return mapToDto(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        User user= userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User","id",id)
        );
       userRepository.delete(user);
    }
}
