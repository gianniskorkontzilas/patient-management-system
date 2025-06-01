@Data
public class LoginRequest {
    private String username;
    private String password;
}
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
