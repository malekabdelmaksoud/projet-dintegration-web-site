package tn.iset.sgs.repository;
import tn.iset.sgs.entity.InternshipOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface InternshipOfferRepository extends JpaRepository<InternshipOffer,Long> {
  List<InternshipOffer> findByStatus(String status);
}
