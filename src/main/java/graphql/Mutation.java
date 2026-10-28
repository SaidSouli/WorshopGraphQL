package graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import entities.Option;
import entities.Etudiant;
import repository.OptionBusiness;
import repository.EtudiantBusiness;

public class Mutation implements GraphQLMutationResolver {

    private final OptionBusiness optionBusiness;
    private final EtudiantBusiness etudiantBusiness;

    public Mutation(OptionBusiness optionBusiness,
                    EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // OPTIONS

    public boolean addOption(
            int codeOption,
            String libelle,
            String domaine,
            String responsable,
            int credits,
            int semestre,
            int capacite) {

        Option option = new Option(
                codeOption, libelle, domaine, responsable,
                credits, semestre, capacite
        );

        return optionBusiness.addOption(option);
    }

    public boolean deleteOption(int code) {
        return optionBusiness.deleteOption(code);
    }

    public boolean updateOption(
            int code,
            String libelle,
            String domaine,
            String responsable,
            int credits,
            int semestre,
            int capacite) {

        Option updatedOption = new Option(
                code,
                libelle,
                domaine,
                responsable,
                credits,
                semestre,
                capacite
        );

        return optionBusiness.updateOption(code, updatedOption);
    }

    // ETUDIANTS

    public boolean addEtudiant(
            String identifiant,
            String nom,
            String prenom,
            int codeOption,
            int anneeEtude,
            String email) {

        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return false;
        }

        Etudiant etudiant = new Etudiant(
                identifiant,
                nom,
                prenom,
                option,
                anneeEtude,
                email
        );

        return etudiantBusiness.addEtudiant(etudiant);
    }

    public boolean updateEtudiant(
            String identifiant,
            String nom,
            String prenom,
            int codeOption,
            int anneeEtude,
            String email) {

        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return false;
        }

        Etudiant updatedEtudiant = new Etudiant(
                identifiant,
                nom,
                prenom,
                option,
                anneeEtude,
                email
        );

        return etudiantBusiness.updateEtudiant(
                identifiant, updatedEtudiant
        );
    }

    public boolean deleteEtudiant(String identifiant) {
        return etudiantBusiness.deleteEtudiant(identifiant);
    }
}