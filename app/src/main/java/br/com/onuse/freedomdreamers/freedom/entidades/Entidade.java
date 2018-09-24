package br.com.onuse.freedomdreamers.freedom.entidades;

import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.onuse.freedomdreamers.freedom.managers.EntidadeManager;
import br.com.onuse.freedomdreamers.freedom.managers.HUDManager;
import br.com.onuse.freedomdreamers.freedom.managers.NucleoManager;

public class Entidade {
        public Object i;
        public int hp, maxHp, atk;
        public double x, y, oldX;
        public String name;
        public EAState state = EAState.IDLE;
        public int currAlpha = 0;
        public boolean shaking = false, fadingIn = false, fadingOut = false, alive = false;
        private int leftTick = 0;
        private int rightTick = 0;
        private int fadeTick = 0;
        private int shakeTick = 0;
        private int maxShakeTicks = 0;
        private int maxFadeTicks = 0;
        private boolean shakeLeft = false, shakeRight = false;

        /**
         *
         * @param name O nome da Entidade
         * @param hp Inicia o HP máximo da Entidade
         * @param atk O valor de attack da Entidade
         * @param x A posição x da Entidade
         * @param y A posição y da Entidade
         * @param startingAlpha Opacidade inicial da Entidade
         */
        public Entidade(String name, int hp, int atk, int x, int y, int startingAlpha){

            currAlpha = startingAlpha;
            this.name = name;
            this.hp = hp;
            this.maxHp = hp;
            this.atk = atk;
            this.x = x;
            this.y = y;
            this.oldX = x;

            // Usado para comparar este objeto em listas
            i = this;

            spawn();
        }

        /**
         * Seta a entidade para o novo estado de animação
         * @param newState The new {@link EAState} to switch to
         */
        public void setState(EAState newState){
            state = newState;
        }

        /**
         * Retorna  o nome da entidade
         * @return O nome
         */
        public String getName(){
            return name;
        }

        /**
         * Retorna o atual estado de animação.
         * @return {@link EAState}
         */
        public EAState getState(){
            return state;
        }

        /**
         * Seta o HP máximo da entidade
         * @param maxHp O valor máximo de HP desejado
         */
        public void setMaxHP(int maxHp){
            this.maxHp = maxHp;
        }

        /**
         * Retorna o HP atual da entidade
         * @return o valor do HP
         */
        public int getHP(){
            return hp;
        }

        /**
         * Retorna o HP da entidade.
         * @return o HP máximo
         */
        public int getMaxHP(){
            return maxHp;
        }

        /**
         * Retorna o valor de ataque.
         * @return O valor de ataque
         */
        public int getAtk(){
            return atk;
        }

        /**
         * Danifica a Entidade, fazendo com que ela perca HP.
         * @param amount Valor do dano
         */
        public void damage(int amount){
            if (hp - amount > 0){
                hp -= amount;
            }else{
                hp = 0;
            }
        }

        /**
         * Cura a Entidade, fazendo com que ela ganhe HP.
         * @param amount O valor da cura
         */
        public void heal(int amount){
            if (hp + amount < maxHp){
                hp += amount;
            }else{
                hp = maxHp;
            }
        }

        /**
         * Checa se a entidade foi morta
         * @return {@code true} se morta
         */
        public boolean isDead(){
            return (hp == 0);
        }

        /**
         * Ticks, gerencia o esmaecimento e movimentos
         */
        public void tick(){
            if (shaking){
                // Começa mexendo pela esquerda
                if (shakeTick == 0){
                    shakeLeft = true;
                    shakeRight = false;
                }
                // Certifique-se de que não atingiu os ticks máximos
                if (shakeTick < maxShakeTicks){
                    shakeTick++;
                    if (shakeLeft){
                        x -= HUDManager.getSpeed(NucleoManager.largura, 600);
                        leftTick += 1;
                        // Allow seven ticks left
                        if (leftTick == 7){
                            // Switch to right
                            leftTick = 0;
                            shakeLeft = false;
                            shakeRight = true;
                        }
                    }
                    if (shakeRight){
                        x += HUDManager.getSpeed(NucleoManager.largura, 600);
                        rightTick += 1;
                        // Allow seven ticks right
                        if (rightTick == 7){
                            // Switch to left
                            rightTick = 0;
                            shakeRight = false;
                            shakeLeft = true;
                        }
                    }
                }
                else{
                    // Máximo de ticks atingidos, redefinir
                    shaking = false;
                    shakeTick = 0;
                    maxShakeTicks = 0;
                    leftTick = 0;
                    rightTick = 0;
                    x = oldX;
                }
            }
            if (fadingIn){
                if (fadeTick < maxFadeTicks){
                    // Incremente o alfa uniformemente de acordo com o tempo total do tick
                    int amount = 255 / maxFadeTicks + 1;
                    fadeTick++;

                    if (currAlpha + amount < 255){
                        currAlpha += amount;
                    }else{
                        currAlpha = 255;
                    }
                }else{
                    // Máximo de ticks atingidos, redefinir
                    fadingIn = false;
                    fadeTick = 0;
                    maxFadeTicks = 0;
                }
            }
            if (fadingOut){
                if (fadeTick < maxFadeTicks){
                    // Inc alpha uniformemente
                    int amount = 255 / maxFadeTicks + 1;
                    fadeTick++;

                    if (currAlpha - amount > 0){
                        currAlpha -= amount;
                    }else{
                        currAlpha = 0;
                    }
                }else{
                    // Máximo de ticks atingidos, redefinir
                    fadingOut = false;
                    fadeTick = 0;
                    maxFadeTicks = 0;
                }
            }
        }

        /**
         * Sacode a Entidade, da esquerda para a direita.
         * @param ticks A quantidade de tempo para agitar, em ticks
         */
        public void shake(int ticks){
            shaking = true;
            maxShakeTicks = ticks;
            oldX = x;
        }

        /**
         * Spawn A entidade, inicia o gráfico
         */
        public void spawn(){
            alive = true;
            EntidadeManager.adicionarEntidade(this);
        }

        /**
         * Remove esta entidade, não afeta outras se não chamado.
         */
        public void destroy(){
            alive = false;
            EntidadeManager.removerEntidade(this);
        }

        /**
         * Efeito de esmaecimento da entidade
         * @param ticks quantidade de ticks determina o tempo de esmaecimento
         */
        public void fadeIn(int ticks){
            fadingIn = true;
            maxFadeTicks = ticks;
        }

        /**
         * Efeito de esmaecimento da entidade
         * @param ticks quantidade de ticks determina o tempo de esmaecimento
         */
        public void fadeOut(int ticks){
            fadingOut = true;
            maxFadeTicks = ticks;
        }

        /**
         * Renderiza a entidade
         */
        public void render(Canvas canvas, Paint paint){}

        /**
         * Verifica se a Entidade é igual a outro objeto.
         * @param obj objeto para ser comparado
         * @return {@code true} se for igual
         */
        public boolean equals(Object obj){
            if (obj == null) return false;
            if (obj == this) return true;
            if (!(obj instanceof Entidade)) return false;
            Entidade o = (Entidade) obj;
            return o.i == this.i;
        }
}
