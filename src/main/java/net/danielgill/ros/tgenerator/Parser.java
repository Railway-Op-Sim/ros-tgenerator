package net.danielgill.ros.tgenerator;

import net.danielgill.ros.tgenerator.rtt.RTTService;
import net.danielgill.ros.timetable.Timetable;
import net.danielgill.ros.timetable.service.Service;

public abstract class Parser {
    public abstract Service genService(RTTService rtt, Timetable ttb);
}
