package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {

  @Autowired
  private UsersRepository usersRepository;

  public User findById(Integer id) {
    return this.usersRepository.findById(id);
  }

  public List<User> findAll() {
    return this.usersRepository.findAll();
  }

  public User save(User user) {
    return this.usersRepository.save(user);
  }

  public User update(User user) {
    User entity = this.findById(user.getId());

    if (user.getName() == null) user.setName(entity.getName());
    if (user.getAge() == 0) user.setAge(entity.getAge());

    return this.usersRepository.update(entity, user);
  }

  public void delete(Integer id) {
    User user = this.usersRepository.findById(id);
    this.usersRepository.delete(user);
  }
}
