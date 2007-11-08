package facades;

import com.oreon.olympics.domain.service.CountryService;

import com.oreon.olympics.domain.service.TeamService;

import com.oreon.olympics.domain.service.EventService;

import com.oreon.olympics.domain.service.AthleteService;

import com.oreon.olympics.domain.service.EventInstanceService;

import com.oreon.olympics.domain.service.CategoryService;

import com.oreon.olympics.domain.service.TournamentService;

import com.oreon.olympics.domain.service.TeamEventInstanceService;

import com.oreon.olympics.domain.service.ParticipationService;

public class ServiceFacade {

	private CountryService countryService;

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	private TeamService teamService;

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	private EventService eventService;

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	private AthleteService athleteService;

	public AthleteService getAthleteService() {
		return athleteService;
	}

	public void setAthleteService(AthleteService athleteService) {
		this.athleteService = athleteService;
	}

	private EventInstanceService eventInstanceService;

	public EventInstanceService getEventInstanceService() {
		return eventInstanceService;
	}

	public void setEventInstanceService(
			EventInstanceService eventInstanceService) {
		this.eventInstanceService = eventInstanceService;
	}

	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private TournamentService tournamentService;

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	private TeamEventInstanceService teamEventInstanceService;

	public TeamEventInstanceService getTeamEventInstanceService() {
		return teamEventInstanceService;
	}

	public void setTeamEventInstanceService(
			TeamEventInstanceService teamEventInstanceService) {
		this.teamEventInstanceService = teamEventInstanceService;
	}

	private ParticipationService participationService;

	public ParticipationService getParticipationService() {
		return participationService;
	}

	public void setParticipationService(
			ParticipationService participationService) {
		this.participationService = participationService;
	}

}
