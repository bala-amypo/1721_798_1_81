public AuthController(AuthenticationManager authenticationManager,
                      UserService userService,
                      JwtUtil jwtUtil,
                      PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
}
