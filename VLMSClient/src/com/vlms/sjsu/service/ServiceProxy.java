package com.vlms.sjsu.service;

public class ServiceProxy implements com.vlms.sjsu.service.Service {
  private String _endpoint = null;
  private com.vlms.sjsu.service.Service service = null;
  
  public ServiceProxy() {
    _initServiceProxy();
  }
  
  public ServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceProxy();
  }
  
  private void _initServiceProxy() {
    try {
      service = (new com.vlms.sjsu.service.ServiceServiceLocator()).getService();
      if (service != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)service)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (service != null)
      ((javax.xml.rpc.Stub)service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.vlms.sjsu.service.Service getService() {
    if (service == null)
      _initServiceProxy();
    return service;
  }
  
  public java.lang.String createUpdateUser(com.vlms.sjsu.entity.User objUser) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.createUpdateUser(objUser);
  }
  
  public com.vlms.sjsu.entity.User[] signIn(java.lang.String strUsername, java.lang.String mdPassword) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.signIn(strUsername, mdPassword);
  }
  
  public com.vlms.sjsu.entity.Movie[] listAllMovies(int count) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.listAllMovies(count);
  }
  
  public com.vlms.sjsu.entity.User[] findUser(com.vlms.sjsu.entity.User objUser) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.findUser(objUser);
  }
  
  public com.vlms.sjsu.entity.Movie[] findMovie(com.vlms.sjsu.entity.Movie objMovie) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.findMovie(objMovie);
  }
  
  public com.vlms.sjsu.entity.Bill[] generateBill(java.lang.String strUserId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.generateBill(strUserId);
  }
  
  public java.lang.String createMovie(com.vlms.sjsu.entity.Movie objMovie) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.createMovie(objMovie);
  }
  
  public java.lang.String updateMovie(com.vlms.sjsu.entity.Movie newMovieObj) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.updateMovie(newMovieObj);
  }
  
  public java.lang.String deleteMovie(java.lang.String strMovieId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.deleteMovie(strMovieId);
  }
  
  public java.lang.String issueMovie(java.lang.String strMovieId, com.vlms.sjsu.entity.User objUser) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.issueMovie(strMovieId, objUser);
  }
  
  public com.vlms.sjsu.entity.User getUserDetails(int strUserId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getUserDetails(strUserId);
  }
  
  public com.vlms.sjsu.entity.Movie[] getUserMovieslist(int strUserId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getUserMovieslist(strUserId);
  }
  
  public com.vlms.sjsu.entity.Movie getMovieDetails(int strMovieId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.getMovieDetails(strMovieId);
  }
  
  public com.vlms.sjsu.entity.User[] movierenteduserlist(int strMovieId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.movierenteduserlist(strMovieId);
  }
  
  public java.lang.String returnMovie(int strMovieId, int strUserId) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.returnMovie(strMovieId, strUserId);
  }
  
  public com.vlms.sjsu.entity.User[] listAllUsers(java.lang.String noOfRows) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.listAllUsers(noOfRows);
  }
  
  public java.lang.String deleteUser(java.lang.String strUserID) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.deleteUser(strUserID);
  }
  
  public com.vlms.sjsu.entity.Movie[] showMoviesToReturn(java.lang.String strUserID) throws java.rmi.RemoteException{
    if (service == null)
      _initServiceProxy();
    return service.showMoviesToReturn(strUserID);
  }
  
  
}