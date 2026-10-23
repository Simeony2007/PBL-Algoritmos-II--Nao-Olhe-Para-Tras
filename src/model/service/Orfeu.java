package src.model.service;

public class Orfeu{
	private int obol;
	private int love;
	private int angry;
	private int sadness;

	// Construtor
	public Orfeu(int obol, int love, int angry, int sadness){
		this.obol = obol;
		this.love = love;
		this.angry = angry;
		this.sadness = sadness;
	}
	// -----------------

	public String toString(){
		return ("	   " + "[Obolos: " + obol + "]\n" + "[Amor: " + love + " | Tristeza: " + sadness + " | Raiva: " + angry + "]");
	}

	public void changeObol(int value){
        obol = Math.max(0, Math.min(99, obol + value));
    }
    public void changeLove(int value){
        love = Math.max(0, Math.min(99, love + value));
    }
    public void changeAngry(int value){
        angry = Math.max(0, Math.min(99, angry + value));
    }
    public void changeSadness(int value){
        sadness = Math.max(0, Math.min(99, sadness + value));
    }

	public int getObol(){
		return obol;
	}
	public int getLove(){
		return love;
	}
	public int getAngry(){
		return angry;
	}
	public int getSadness(){
		return sadness;
	}
}