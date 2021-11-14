package net.danielgill.ros.tgenerator;

import net.danielgill.ros.service.Service;

public abstract class Parser {
    public abstract Service genService(RTTParse rtt);
}
