package net.joritan.jlime.world.object.entity.segment;

import org.jbox2d.dynamics.Body;

public interface Segment
{
    public Body getBody();

    public void update(float timeDelta);
    public void render();
}
