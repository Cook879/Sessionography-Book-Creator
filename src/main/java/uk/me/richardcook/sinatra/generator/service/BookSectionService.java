package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.SectionDao;
import uk.me.richardcook.sinatra.generator.model.BookFile;
import uk.me.richardcook.sinatra.generator.model.Section;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookSectionService {

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private BookSessionService bookSessionService;

	public List<Section> findAll() {
		return sectionDao.findAll();
	}

	public void writeAll( BookFile book ) {
		List<Section> sections = findAll();

		for ( Section section : sections ) {
			book.printChapter( section.getTitle() );
			book.println( BookFile.removeSpecialCharacters( section.getDescription() ) );

			bookSessionService.writeForSection( book, section.getId() );
		}
	}

	public Map<Integer, Section> findAllMap() {
		List<Section> sections = findAll();
		Map<Integer, Section> map = new HashMap<Integer, Section>();
		for ( Section section : sections ) {
			map.put( section.getId(), section );
		}
		return map;
	}
}
