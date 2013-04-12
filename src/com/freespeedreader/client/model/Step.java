package com.freespeedreader.client.model;

public class Step {
    private double endIndex;

    private int noOfWords;

    private double startIndex;
    
    private double y;

    public Step(double startIndex, double endIndex,double y,int noOfWords) {
        super();
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.noOfWords=noOfWords;
        this.y=y;
    }


	public double getEndIndex() {
		return endIndex;
	}


	public int getNoOfWords() {
        return noOfWords;
    }


    public double getStartIndex() {
		return startIndex;
	}

    public double getY() {
		return y;
	}


	public void setEndIndex(double endIndex) {
		this.endIndex = endIndex;
	}


	public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }


	public void setNoOfWords(int noOfWords) {
        this.noOfWords = noOfWords;
    }


	public void setStartIndex(double startIndex) {
		this.startIndex = startIndex;
	}

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setY(double y) {
		this.y = y;
	}

    @Override
    public String toString() {
        return "Step [startIndex=" + startIndex + ", endIndex=" + endIndex
                + ", noOfWords=" + noOfWords + "]";
    }
}
