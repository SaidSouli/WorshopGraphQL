package graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import entities.Option;
import entities.Etudiant;
import repository.OptionBusiness;
import repository.EtudiantBusiness;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final OptionBusiness optionBusiness;
    private final EtudiantBusiness etudiantBusiness;

    public Query(OptionBusiness optionBusiness,
                 EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // OPTIONS

    public List<Option> getAllOptions() {
        return optionBusiness.getListeOptions();
    }

    public Option getOptionByCode(int code) {
        return optionBusiness.getOptionByCode(code);
    }

    public List<Option> getOptionsByDomaine(String domaine) {
        return optionBusiness.getOptionsByDomaine(domaine);
    }

    // ETUDIANTS

    public List<Etudiant> getAllEtudiants() {
        return etudiantBusiness.getAllEtudiants();
    }

    public Etudiant getEtudiantByIdentifiant(String identifiant) {
        return etudiantBusiness.getEtudiantByIdentifiant(identifiant);
    }

    public List<Etudiant> getEtudiantsByOption(int codeOption) {
        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return null;
        }

        return etudiantBusiness.getEtudiantsByOption(option);
    }
}