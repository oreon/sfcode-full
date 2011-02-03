package com.nas.recovery.websvc.timetrack;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.timetrack.TimeTrackingEntry;

@WebService(endpointInterface = "com.nas.recovery.websvc.timetrack.TimeTrackingEntryWebService", serviceName = "TimeTrackingEntryWebService")
@Name("timeTrackingEntryWebService")
public class TimeTrackingEntryWebServiceImpl
		implements
			TimeTrackingEntryWebService {

	@In(create = true)
	com.nas.recovery.web.action.timetrack.TimeTrackingEntryAction timeTrackingEntryAction;

	public TimeTrackingEntry loadById(Long id) {
		return timeTrackingEntryAction.loadFromId(id);
	}

	public List<TimeTrackingEntry> findByExample(
			TimeTrackingEntry exampleTimeTrackingEntry) {
		return timeTrackingEntryAction.search(exampleTimeTrackingEntry);
	}

	public void save(TimeTrackingEntry timeTrackingEntry) {
		timeTrackingEntryAction.persist(timeTrackingEntry);
	}

}
