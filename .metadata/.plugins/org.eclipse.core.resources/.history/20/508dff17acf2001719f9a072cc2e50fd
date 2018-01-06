package com.bridgelabz.User.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.User.Service.NoteServices;

public class ScheduleNote {
	
	@Autowired
	NoteServices noteServices;
	
	public void noteSchedule()
	{
		System.out.println("Before Schedular.......");
		noteServices.deleteScheduleNote();
		System.out.println("After Schedular.......");
	}

}
