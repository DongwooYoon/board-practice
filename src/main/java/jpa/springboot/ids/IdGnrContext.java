package jpa.springboot.ids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGnrContext {
	
	
	@Bean(name = "leagueoflegendsBoardIdGnrService")
	public IdGnrService leagueoflegendsBoardIdGnrService() {
		IdGnrService idGnrService = new IdGnrService();
		idGnrService.setCipers(5);
		idGnrService.setFillChar('0');
		idGnrService.setPrefix("LOL_");
		idGnrService.setTableName("LOL_ID");
		return idGnrService;
	}
	
	@Bean(name = "fifaonline4BoardIdGnrService")
	public IdGnrService fifaonline4BoardIdGnrService() {
		IdGnrService idGnrService = new IdGnrService();
		idGnrService.setCipers(5);
		idGnrService.setFillChar('0');
		idGnrService.setPrefix("FIFA_");
		idGnrService.setTableName("FIFA_ID");
		return idGnrService;
	}
	
	@Bean(name = "suddenattackBoardIdGnrService")
	public IdGnrService suddenattackBoardIdGnrService() {
		IdGnrService idGnrService = new IdGnrService();
		idGnrService.setCipers(5);
		idGnrService.setFillChar('0');
		idGnrService.setPrefix("SA_");
		idGnrService.setTableName("SA_ID");
		return idGnrService;
	}
	
	@Bean(name = "battlegroundsBoardIdGnrService")
	public IdGnrService battlegroundsBoardIdGnrService() {
		IdGnrService idGnrService = new IdGnrService();
		idGnrService.setCipers(5);
		idGnrService.setFillChar('0');
		idGnrService.setPrefix("PUBG_");
		idGnrService.setTableName("PUBG_ID");
		return idGnrService;
	}
}