/**
 * Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vlms.sjsu.service;

public interface Service extends java.rmi.Remote {
    public java.lang.String createUpdateUser(com.vlms.sjsu.entity.User objUser) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.User[] signIn(java.lang.String strUsername, java.lang.String mdPassword) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.Movie[] listAllMovies(int count) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.User[] findUser(com.vlms.sjsu.entity.User objUser) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.Movie[] findMovie(com.vlms.sjsu.entity.Movie objMovie) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.Bill[] generateBill(java.lang.String strUserId) throws java.rmi.RemoteException;
    public java.lang.String createMovie(com.vlms.sjsu.entity.Movie objMovie) throws java.rmi.RemoteException;
    public java.lang.String updateMovie(com.vlms.sjsu.entity.Movie newMovieObj) throws java.rmi.RemoteException;
    public java.lang.String deleteMovie(java.lang.String strMovieId) throws java.rmi.RemoteException;
    public java.lang.String issueMovie(java.lang.String strMovieId, com.vlms.sjsu.entity.User objUser) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.User getUserDetails(int strUserId) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.Movie[] getUserMovieslist(int strUserId) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.Movie getMovieDetails(int strMovieId) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.User[] movierenteduserlist(int strMovieId) throws java.rmi.RemoteException;
    public java.lang.String returnMovie(int strMovieId, int strUserId) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.User[] listAllUsers(java.lang.String noOfRows) throws java.rmi.RemoteException;
    public java.lang.String deleteUser(java.lang.String strUserID) throws java.rmi.RemoteException;
    public com.vlms.sjsu.entity.Movie[] showMoviesToReturn(java.lang.String strUserID) throws java.rmi.RemoteException;
}
