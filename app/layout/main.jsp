<div ng-repeat="component in headerMenu.components">
	<div id="{{component.name}}" ng-show="{{component.isEnabled}}" ng-include="component.link"></div>
</div> 


<!-- <div ng-include="'app/headerMenu/headerMenu.jsp'"></div> -->

<!-- <div ng-include="'app/header/header.jsp'"></div> 

<div id="home" ng-include="'app/home/home.jsp'"></div> 			

<div id="contact" ng-include="'app/contact/contact.jsp'"></div> 			

<div id="projects" ng-include="'app/projects/projects.jsp'"></div> 			

<div id="profile" ng-include="'app/profile/profile.jsp'"></div>   -->