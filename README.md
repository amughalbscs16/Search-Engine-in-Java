# Search Engine indexing the Xml Files in Single Word O(1), MultiWord O(N Log(N)) Complexity
	This is our Datastructures and Algorithms project, I ranked top in the class for this project.
# Project Features:
	A Large corpus of wikipedia XML file was parsed using SAXParser Java's API.
	The pages were split into Title and Body and were cleaned up using the stem word processor to remove extra keywords.
	Page Rank calculated using the position of words and the occurance of words according to the location and Title/Body weightage.
	Hashmaps were used for the implementation of search and then the results are converted from HashMap to Array list and then Sorted.
# Forward and Reverse Indexing
	All the pages were split into words and the count of each word on each page was stored in a file and the related page rank of that page with respect to that word
	Each word was a text directory containing the page link and page rank
# Single Word Searching
	For Single word searching simply the word.txt file is opened and the results are displayed as they are already sorted
# Multi Word Searching
	For Multi Word searching the multiple word.txt files are opened and put into a hash map and the cummulative page rank of each page is calculated and sorted.
	Then the top results are shown to the user.
	
