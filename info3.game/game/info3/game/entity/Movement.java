package info3.game.entity;

import info3.game.GameSession;

public class Movement {

    static public void Walk(Entity E) {
        if (E.stFaceLeft()) {
            if (E.velX > E.model.maxVelX) {
                E.velX = E.model.maxVelX;
            } // capping player speed
            E.x -= E.velX; // moving E
        }
        if (E.stFaceRight()) {
            if (E.velX > E.model.maxVelX) {
                E.velX = E.model.maxVelX;
            } // same as above except other direction
            E.x += E.velX;
        }

    }

    // here everything that handles jumping and gravity affectd movement
    static public void jump(Entity E, long deltatime) {
        if (E.statusJump()) {
            System.out.println("Vely:"+E.velY+" ajd jumptime:"+E.jumptime+" and E.y:"+E.y+" cd:"+E.jumpcd);
            //affect jump when key pressed
            if (E.jumptime == 0 && !E.jumpcd) {
                E.jumpcd = true;
                E.jumptime = 60;
                E.velY = -PhysicConstant.gravity * PhysicConstant.lowJumpmultiplier * deltatime;
                //affect Y velocity if still wqnting to jump aka Z key not release
            } else if (E.jumptime > 0) {
                E.jumptime-= deltatime;
                E.velY = ((E.jumptime / 2.2f) * 1.1f);
                E.jumpcd = false;
                //E.velY=1;
            } else {
                //end of jump, starting to fall
                E.velY += PhysicConstant.gravity * (PhysicConstant.fallmultiplier - 1) * deltatime;
            }
        //deccelerate jump if z key released early
        } else if (E.jumptime > 0) {
            E.velY *= 0.35f;
            //E.velY=1;
            E.jumptime = 0;
        //affect gravity
        } else {
            E.jumpcd = false;
            E.jumptime = 0;
            E.velY += PhysicConstant.gravity * (PhysicConstant.fallmultiplier - 1) * deltatime;
        }
        E.y -= E.velY;
    }

}