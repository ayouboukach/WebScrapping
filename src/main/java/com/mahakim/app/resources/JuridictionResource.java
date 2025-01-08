package com.mahakim.app.resources;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahakim.app.response.model.HttpResponse;
import com.mahakim.app.services.juridictions.JuridictionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/juridiction")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class JuridictionResource {

	private final JuridictionService juridictionService;

	@PostMapping("/scrappe")
	public ResponseEntity<?> addJuridictions(@RequestParam("codeDossier") String codeSossier) {
		juridictionService.createJuridictions(codeSossier);
		return ResponseEntity.ok().body(createHttpResponse(HttpStatus.OK, "Juridictions is scrapped successfully",null));
	}

	@GetMapping("/all")
//    @PreAuthorize("permitAll()")
	public ResponseEntity<?> getAllJuridictions() {
		return ResponseEntity.ok().body(createHttpResponse(HttpStatus.OK, "Listes of Juridictions",(Map<?, ?>) juridictionService.getJuridictions()));
	}

	/*
	 * @PostMapping("/refreshtoken") public ResponseEntity<?>
	 * refreshtoken(HttpServletRequest request) throws InvalidKeyException,
	 * SignatureException, NoSuchAlgorithmException { String refreshToken =
	 * jwtTokenProvider.getJwtRefreshFromCookies(request); if ((refreshToken !=
	 * null) && (refreshToken.length() > 0)) { String tokenHashed =
	 * StaticMethods.calculateHMAC(refreshToken, secretRefreshToken); return
	 * refreshTokenService.findByToken(tokenHashed)
	 * .map(refreshTokenService::verifyExpiration) .map(RefreshTokenDto::getUser)
	 * .map(user -> { String token = jwtTokenProvider.generateJwtToken(new
	 * UserPrincipal(user)); return ResponseEntity.ok() .header(JWT_TOKEN_HEADER,
	 * token) .body(createHttpResponse(OK, "Token is refreshed successfully!")); })
	 * .orElseThrow(() -> new TokenRefreshException(refreshToken,
	 * "Refresh token is not in database!")); }
	 * 
	 * return
	 * ResponseEntity.badRequest().body(createHttpResponse(HttpStatus.BAD_REQUEST,
	 * "Refresh Token is empty!")); }
	 * 
	 * @PostMapping("/add-user") public ResponseEntity<UserDto>
	 * createUser(@Valid @RequestBody UserRequest user) throws AlreadyExistException
	 * { UserDto userSaved = userService.createUser(user); return new
	 * ResponseEntity<>(userSaved, OK); }
	 */

	HttpResponse createHttpResponse(HttpStatus httpStatus, String message,Map<?, ?> data) {
		return new HttpResponse(httpStatus.value(), httpStatus, message);
	}
}
