import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        User u = new User();
        u.setFullName(req.fullName);
        u.setEmail(req.email);
        u.setDepartment(req.department);
        u.setPassword(req.password);
        return userService.registerUser(u);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        User user = userService.findByEmail(req.email);
        return jwtUtil.generateToken(user);
    }
}
