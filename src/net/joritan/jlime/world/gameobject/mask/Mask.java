package net.joritan.jlime.world.gameobject.mask;

import net.joritan.jlime.util.Texture;
import net.joritan.jlime.util.Vector2;
import org.jbox2d.common.Vec2;

import static org.lwjgl.opengl.GL11.*;

public class Mask
{
    private int vertexCount;

    private Texture texture;
    private Vector2[] vertices;
    private Vector2[] texcoords;

    private Vector2 translation;
    private float angle;

    public Mask(Texture texture, Vector2[] vertices, Vector2 translation, float angle, boolean isStatic)
    {
        vertexCount = vertices.length;

        this.texture = texture;
        this.vertices = vertices;
        if(isStatic)
        {
            this.texcoords = new Vector2[vertexCount];
            for (int i = 0; i < vertexCount; i++)
                texcoords[i] = new Vector2(vertices[i].x + translation.x, -(vertices[i].y + translation.y));
        }

        this.translation = translation;
    }

    public Mask(Texture texture, Vector2[] vertices, Vector2 translation, float angle)
    {
        this(texture, vertices, translation, angle, true);
    }

    public Mask(Texture texture, Vector2[] vertices, Vector2[] texcoords, Vector2 translation, float angle)
    {
        this(texture, vertices, translation, angle, false);
        this.texcoords = texcoords;
    }

    public void setTranslation(Vector2 translation)
    {
        this.translation = translation;
    }

    public void setAngle(float angle)
    {
        this.angle = angle;
    }

    public void update(float timeDelta)
    {

    }

    public void render()
    {
        texture.bind();

        glTranslatef(translation.x, translation.y, 0.0f);
        glRotatef((float) Math.toDegrees(angle), 0.0f, 0.0f, 1.0f);

        glBegin(GL_POLYGON);
        for(int i = 0; i < vertexCount; i++)
        {
            glTexCoord2f(texcoords[i].x, texcoords[i].y);
            glVertex2f(vertices[i].x, vertices[i].y);
        }
        glEnd();

        glBindTexture(GL_TEXTURE_2D, 0);
    }
}