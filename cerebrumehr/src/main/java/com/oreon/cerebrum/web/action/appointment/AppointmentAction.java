package com.oreon.cerebrum.web.action.appointment;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.event.internal.OnLockVisitor;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Conversational;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.joda.time.DateTime;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.oreon.cerebrum.appointment.Appointment;

@Scope(ScopeType.SESSION)
@Name("appointmentAction")
public class AppointmentAction extends AppointmentActionBase implements
		java.io.Serializable {

	private ScheduleModel eventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();

	public ScheduleModel getEventModel() {
		if (eventModel == null) {
			updateEvents();
		}

		return eventModel;
	}

	private void updateEvents() {
		eventModel = new DefaultScheduleModel();
		AppointmentListQuery appointmentListQuery = (AppointmentListQuery) Component
				.getInstance("appointmentList");

		List<Appointment> appts = appointmentListQuery.getAll();
		for (Appointment appointment : appts) {
			addAppointmentToSchedule(appointment, null);
		}
	}

	private void addAppointmentToSchedule(Appointment appointment,
			DefaultScheduleEvent evt) {

		if (evt == null)
			evt = new DefaultScheduleEvent("", appointment.getStart(),
					appointment.getEnd());

		evt.setTitle(appointment.getPatient().getDisplayName());
		evt.setData(appointment.getId());
		// evt.setId(appointment.getId().toString());
		eventModel.addEvent(evt);
	}

	public void addEvent(ActionEvent actionEvent) {
		save();
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
		load((Long) event.getData());
		// updateAppointmentFromScheduleEvent(getInstance(), (ScheduleEvent)
		// selectEvent.getObject());
	}

	@Begin(join = true)
	public void onDateSelect(SelectEvent selectEvent) {
		clearInstance();

		Date start = (Date) selectEvent.getObject();
		System.out.println(start);

		DateTime dtEnd = new DateTime(start);
		dtEnd = dtEnd.plusMinutes(30);

		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				dtEnd.toDate());

		getInstance().setStart(event.getStartDate());
		if (Conversation.instance().getId() == null)
			Conversation.instance().begin(true, false);
		System.out.println("current conersation bef "
				+ Conversation.instance().getId());
		// instance.setUnits(4);
	}

	@Override
	@Begin(join = true)
	public String saveConversational() {
		// instance.setStart(event.getStartDate());

		System.out.println("current conersation end "
				+ Conversation.instance().getId());

		DateTime dtEnd = new DateTime(getInstance().getStart());
		dtEnd = dtEnd.plusMinutes(30 * instance.getUnits());
		instance.setEnd(dtEnd.toDate());

		((DefaultScheduleEvent) event).setEndDate(getInstance().getEnd());

		// else
		if (!isNew())
			eventModel.updateEvent(event);

		return super.saveConversational();
	}

	@Override
	protected void postSave() {
		if (event.getData() == null)
			addAppointmentToSchedule(instance, (DefaultScheduleEvent) event);
		super.postSave();
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		Appointment current = entityManager.find(Appointment.class,
				(Long) event.getScheduleEvent().getData());
		updateAppointmentFromScheduleEvent(current, event.getScheduleEvent());
		persist(current);
		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void updateAppointmentFromScheduleEvent(Appointment appt,
			ScheduleEvent scheduleEvent) {
		appt.setStart(scheduleEvent.getStartDate());
		appt.setEnd(scheduleEvent.getEndDate());
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}