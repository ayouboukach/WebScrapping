package com.mahakim.app.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.mahakim.app.services.dossiers.DossierService;
import com.mahakim.app.services.dossiers.decisions.DecisionService;
import com.mahakim.app.utils.HttpResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dossier")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class DossierResource {

	private final DossierService dossierService;
	private final DecisionService decisionService;

	@PostMapping("/scrappe")
	public ResponseEntity<?> addDossiers(@RequestParam("numeroDossier") String numeroDossier,
			@RequestParam("year") String year, @RequestParam("codeDossier") String codeDossier,
			@RequestParam("range") int range, @RequestParam("idJuridiction") int idJuridiction) throws ParseException, StreamReadException, DatabindException, IOException {
		dossierService.createDossiers(year, codeDossier, numeroDossier, range, idJuridiction);
		return ResponseEntity.ok().body(createHttpResponse(HttpStatus.OK, "Dossiers is scrapped successfully", null));
	}

	@PostMapping("/decisionscrapping")
//    @PreAuthorize("permitAll()")
	public ResponseEntity<?> getAllDossiers(@RequestParam("idDossier") String idDossier ) throws NumberFormatException, ParseException{
		return ResponseEntity.ok()
				.body(createHttpResponse(HttpStatus.OK, "Listes of Juridictions", decisionService.saveDecisionsByIdDossierFromAPI(Integer.parseInt(idDossier))));
	}

	@GetMapping("/file")
	String getData() {
		String strLine = "";
		try (BufferedReader br = new BufferedReader(new FileReader(new File(
				"https://www.mahakim.ma/middleware/api/SuiviDossiers/ListeJuridictions2Instance?CodeDossier=1501")))) {
			strLine = br.lines().collect(Collectors.joining("\n")); // this way next line text can also be returned into
																	// existing string & strLine will looks like exactly
																	// as your text file content
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strLine;
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

	HttpResponse createHttpResponse(HttpStatus httpStatus, String message, Object data) {
		return new HttpResponse(httpStatus.value(), httpStatus, message);
	}
}
