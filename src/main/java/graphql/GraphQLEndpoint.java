package graphql;

import repository.OptionBusiness;
import repository.EtudiantBusiness;
import graphql.schema.GraphQLSchema;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint(){
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {

        OptionBusiness optionBusiness = new OptionBusiness();
        EtudiantBusiness etudiantBusiness = new EtudiantBusiness();

        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(optionBusiness,etudiantBusiness),
                    new Mutation(optionBusiness,etudiantBusiness))
                .build()
                .makeExecutableSchema();
    }
}
