@Override
public Map<String, String> login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    Map<String, Object> claims = new HashMap<>();
    claims.put("role", user.getRole());
    claims.put("department", user.getDepartment());

    String token = jwtUtil.generateToken(claims, user.getEmail());

    Map<String, String> response = new HashMap<>();
    response.put("token", token);

    return response;
}
