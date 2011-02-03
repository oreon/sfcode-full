package com.nas.recovery.websvc.timetrack;

import javax.jws.WebService;
import org.wc.trackrite.timetrack.TimeTrackingEntry;
import java.util.List;

@WebService
public interface TimeTrackingEntryWebService {

	public TimeTrackingEntry loadById(Long id);

	public List<TimeTrackingEntry> findByExample(
			TimeTrackingEntry exampleTimeTrackingEntry);

	public void save(TimeTrackingEntry timeTrackingEntry);

}
