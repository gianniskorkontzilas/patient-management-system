@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public JwtResponse register(RegisterRequest req) {
        var user = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .build();

        userRepository.save(user);
        return new JwtResponse(jwtUtil.generateToken(user));
    }

    public JwtResponse login(LoginRequest req) {
        var user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Wrong credentials");

        return new JwtResponse(jwtUtil.generateToken(user));
    }
}
