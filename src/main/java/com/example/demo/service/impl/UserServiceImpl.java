@Override
public Map<String, String> login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid email or password");
    }

    String token = jwtUtil.generateToken(user);

    return Map.of(
            "token", token,
            "email", user.getEmail(),
            "role", user.getRole()
    );
}
