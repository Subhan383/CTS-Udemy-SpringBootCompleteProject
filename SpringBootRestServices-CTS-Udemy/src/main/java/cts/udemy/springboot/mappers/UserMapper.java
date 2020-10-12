package cts.udemy.springboot.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import cts.udemy.springboot.dtos.UserMsDTO;
import cts.udemy.springboot.entity.User;

@Mapper(componentModel="spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({@Mapping(source="email", target="emailaddress"),@Mapping(source="role", target="rolename")})
	UserMsDTO userToUserMsDTO(User user);

	List<UserMsDTO> userToUserMsDTOS(List<User> user);

}
