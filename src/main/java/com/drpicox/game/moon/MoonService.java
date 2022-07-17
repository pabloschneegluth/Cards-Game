package com.drpicox.game.moon;

import com.drpicox.game.util.Settings;
import com.drpicox.game.util.Steps;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoonService {

    private final Steps<EndMoonSettings> endMoonSteps;

    public MoonService(List<EndMoonStep> endMoonSteps) {
        this.endMoonSteps = Steps.from(endMoonSteps);
    }

    public void endMoon() {
        endMoonSteps.execute(new EndMoonSettings());
    }
}
