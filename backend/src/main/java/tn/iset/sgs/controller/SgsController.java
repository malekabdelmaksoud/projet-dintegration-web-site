package tn.iset.sgs.controller;

import tn.iset.sgs.entity.*;
import tn.iset.sgs.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:5173")
public class SgsController {
  private final CompanyRepository companies;
  private final InternshipOfferRepository offers;
  private final InternshipApplicationRepository applications;
  public SgsController(CompanyRepository c, InternshipOfferRepository o, InternshipApplicationRepository a) {
    companies=c; offers=o; applications=a;
  }

  @GetMapping("/companies") public List<Company> companies(){return companies.findAll();}
  @PostMapping("/companies") public Company addCompany(@Valid @RequestBody Company c){return companies.save(c);}

  @GetMapping("/offers") public List<InternshipOffer> offers(){return offers.findAll();}
  @PostMapping("/offers") public InternshipOffer addOffer(@Valid @RequestBody OfferInput input){
    Company c=companies.findById(input.companyId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Entreprise introuvable"));
    InternshipOffer o=new InternshipOffer(); o.setTitle(input.title()); o.setDescription(input.description());
    o.setDeadline(input.deadline()); o.setCompany(c); return offers.save(o);
  }

  @GetMapping("/applications") public List<InternshipApplication> applications(){return applications.findAll();}
  @PostMapping("/applications") public InternshipApplication apply(@RequestBody ApplicationInput in){
    if(in.startDate()==null || in.endDate()==null || !in.endDate().isAfter(in.startDate()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La date de fin doit être après la date de début.");
    InternshipOffer offer=offers.findById(in.offerId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Offre introuvable"));
    if(!"OUVERTE".equals(offer.getStatus()) || (offer.getDeadline()!=null && offer.getDeadline().isBefore(LocalDate.now())))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cette offre n'est plus disponible.");
    InternshipApplication a=new InternshipApplication(); a.setStudentName(in.studentName()); a.setStudentEmail(in.studentEmail());
    a.setSubject(in.subject()); a.setStartDate(in.startDate()); a.setEndDate(in.endDate()); a.setOffer(offer);
    return applications.save(a);
  }

  @PatchMapping("/applications/{id}/decision") public InternshipApplication decide(@PathVariable Long id,@RequestBody DecisionInput in){
    InternshipApplication a=applications.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    if(!List.of("ACCEPTEE","REFUSEE","A_COMPLETER").contains(in.status()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Décision invalide.");
    if(("REFUSEE".equals(in.status()) || "A_COMPLETER".equals(in.status())) && (in.comment()==null || in.comment().isBlank()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Un motif est obligatoire.");
    a.setStatus(in.status()); a.setComment(in.comment()); return applications.save(a);
  }

  public record OfferInput(String title,String description,LocalDate deadline,Long companyId){}
  public record ApplicationInput(String studentName,String studentEmail,String subject,LocalDate startDate,LocalDate endDate,Long offerId){}
  public record DecisionInput(String status,String comment){}
}
