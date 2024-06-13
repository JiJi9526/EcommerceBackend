package Ecommerce.service.serviceImpl;

import Ecommerce.dto.UsersInfo;
import Ecommerce.entity.Users;
import Ecommerce.repo.UsersRepo;
import Ecommerce.service.UsersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepo usersRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public Users createUser(Users user) {
        Users newUser = new Users();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setRole("USER");
        newUser.setCreateDate(new Date());
        newUser.setUpdateDate(new Date());
        return usersRepo.save(newUser);
    }

    @Override
    public Users updateUser(UsersInfo usersInfo) {
        Users user = usersRepo.findById(usersInfo.getId()).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setFirstName(usersInfo.getFirstName() != null ? usersInfo.getFirstName() : user.getFirstName());
        user.setLastName(usersInfo.getLastName() != null ? usersInfo.getLastName() : user.getLastName());
        user.setEmail(usersInfo.getEmail() != null ? usersInfo.getEmail() : user.getEmail());
        user.setUpdateDate(new Date());
        return usersRepo.save(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        usersRepo.deleteById(userId);
    }
}
