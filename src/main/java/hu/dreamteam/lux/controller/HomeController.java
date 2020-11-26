package hu.dreamteam.lux.controller;

import hu.dreamteam.lux.entity.Post;
import hu.dreamteam.lux.entity.User;
import hu.dreamteam.lux.service.PostService;
import hu.dreamteam.lux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private UserService userService;
    private BCryptPasswordEncoder encoder;
    private PostService postService;

    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    //ez egy komment, majd kitörlöm :D
    @Autowired
    private void setEncoder(BCryptPasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Autowired
    private void setPostService(PostService postService){
        this.postService = postService;
    }
// Itt vannak az endpoint-ok és a service példányok
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("current_user_name", request.getUserPrincipal().getName());
        try {
            model.addAttribute("posts", postService.getPosts());
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping("/reg")
    public String reg(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userService.registerUser(user);
        return "auth/login";
    }

    @RequestMapping("/post")
    public String posting(Post post, HttpServletRequest request){
        postService.savePost(post, userService.findByEmail(request.getUserPrincipal().getName()));
        return "redirect:/";
    }

}

