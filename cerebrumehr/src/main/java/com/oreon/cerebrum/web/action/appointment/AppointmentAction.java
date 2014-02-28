     package com.oreon.cerebrum.web.action.appointment;
 
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.eclipse.jdt.internal.core.search.indexing.SaveIndex;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;
import org.joda.time.DateTime;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
import com.oreon.cerebrum.appointment.Appointment;
 
//@Scope(ScopeType.CONVERSATION)
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
 
                  DateTime dtEnd = new DateTime(appointment.getStart());
 
                  dtEnd = dtEnd.plusMinutes(30);
                 
                  DefaultScheduleEvent evt = new DefaultScheduleEvent(appointment
                              .getPatient().getDisplayName()  , appointment.getStart(),
                              new Timestamp(dtEnd.toDate().getTime()));
            //    evt.setData(appointment.getId());
            //    evt.setId(appointment.getId().toString());
                  eventModel.addEvent(evt);
            }
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
      }
 
      public void onDateSelect(SelectEvent selectEvent) {
            event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
                        (Date) selectEvent.getObject());
      }
 
      public void onEventMove(ScheduleEntryMoveEvent event) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Event moved", "Day delta:" + event.getDayDelta()
                                    + ", Minute delta:" + event.getMinuteDelta());
           
            Appointment current = entityManager.find( Appointment.class,  (Long)event.getScheduleEvent().getData() );
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
     
      public void updateAppointmentFromScheduleEvent(Appointment appt, ScheduleEvent scheduleEvent){
            appt.setStart(scheduleEvent.getStartDate());
            appt.setEnd(scheduleEvent.getEndDate());
      }
     
      private void addMessage(FacesMessage message) { 
        FacesContext.getCurrentInstance().addMessage(null, message); 
    } 
 
}