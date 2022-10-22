package com.board.Service;

import com.board.repository.UserRepository;
import com.board.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class UserService {
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    public void login(String userId, String userPw){
        User user = userRepository.findByUserIdAndPassword(userId, userPw);
        httpSession.setAttribute("loginUser",user.getName());
    }

}
