package net.danielgill.ros.tgenerator;

import net.danielgill.ros.service.Service;
import net.danielgill.ros.tgenerator.rtt.RTTService;

public abstract class Parser {
    public abstract Service genService(RTTService rtt, Timetable ttb);
}
