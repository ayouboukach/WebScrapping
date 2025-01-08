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
import com.mahakim.app.response.model.HttpResponse;
import com.mahakim.app.services.dossiers.DossierService;
import com.mahakim.app.services.dossiers.decisions.DecisionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dossier")
@RequiredArgsConstructor
public class DossierResource {

    private final DossierService dossierService;
    private final DecisionService decisionService;

    @PostMapping("/scrappe")
    public ResponseEntity<?> addDossiers(
            @RequestParam("numeroDossier") String numeroDossier,
            @RequestParam("year") String year,
            @RequestParam("codeDossier") String codeDossier,
            @RequestParam("range") int range,
            @RequestParam("idJuridiction") int idJuridiction) 
            throws ParseException, StreamReadException, DatabindException, IOException {

        dossierService.actualiserDossiers(year, codeDossier, numeroDossier, range, idJuridiction);
        return ResponseEntity.ok(createHttpResponse(HttpStatus.OK, "Dossier scrapped successfully", null));
    }

    @PostMapping("/decisionscrapping")
    public ResponseEntity<?> getAllDossiers(@RequestParam("idDossier") String idDossier) throws ParseException {
        var decisionList = decisionService.sauvegarderDecisionsApiIdDossierDansDb(Integer.parseInt(idDossier));
        return ResponseEntity.ok(createHttpResponse(HttpStatus.OK, "List of Decisions", decisionList));
    }
    
    @PostMapping("/referesh-decisions")
    public ResponseEntity<?> refereshDecisions() throws ParseException {
        decisionService.actualiserDecisionsDepuisApiChasque5Secondes();;
        return ResponseEntity.ok(createHttpResponse(HttpStatus.OK, "Decisions of today is refreshed successfully",null));
    }

    @GetMapping("/file")
    public String getData() {
        var url = "https://www.mahakim.ma/middleware/api/SuiviDossiers/ListeJuridictions2Instance?CodeDossier=1501";
        try (var br = new BufferedReader(new FileReader(new File(url)))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file";
        }
    }

    private HttpResponse createHttpResponse(HttpStatus status, String message, Object data) {
        return new HttpResponse(status.value(), status, message);
    }
}
