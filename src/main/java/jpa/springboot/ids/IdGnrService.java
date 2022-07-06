package jpa.springboot.ids;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter @Setter
@NoArgsConstructor
public class IdGnrService {
	protected Log log = LogFactory.getLog(this.getClass());
	private String prefix;
	private int cipers;
	private char fillChar;
	private String tableName;
	
	

	@Autowired
	private IdsRepository idsRepository;
	
	public String getNextStringId() {
		Ids ids = idsRepository.findByTableName(tableName);
		boolean firstFlag = false;
		if (ids == null) {
			ids = new Ids();
			ids.setNextId(1);
			ids.setTableName(tableName);
			firstFlag = true;
		}
		
		String nextId = "";
		nextId = prefix;
		int fillCharSize = cipers - (int)(Math.log10(ids.getNextId()) + 1);
		for(int i=0; i<fillCharSize; i++) {
			nextId = nextId + fillChar;
		}
		if(!firstFlag) {
			ids.setNextId(ids.getNextId()+1);
		}
		idsRepository.save(ids);
		nextId = nextId + String.valueOf(ids.getNextId());
		return nextId;
	}

}
