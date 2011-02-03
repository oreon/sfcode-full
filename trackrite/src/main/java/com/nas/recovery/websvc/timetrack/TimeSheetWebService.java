package com.nas.recovery.websvc.timetrack;

import javax.jws.WebService;
import org.wc.trackrite.timetrack.TimeSheet;
import java.util.List;

@WebService
public interface TimeSheetWebService {

	public TimeSheet loadById(Long id);

	public List<TimeSheet> findByExample(TimeSheet exampleTimeSheet);

	public void save(TimeSheet timeSheet);

}
