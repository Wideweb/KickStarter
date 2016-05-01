<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    <spring:url value="/client/application/js/app.js" var="appJs" />
    <script src="${appJs}"></script>

    <spring:url value="/client/application/js/constants/appSettings.js" var="appSettingsJs" />
    <script src="${appSettingsJs}"></script>

    <spring:url value="/client/application/js/constants/appStates.js" var="appStatesJs" />
    <script src="${appStatesJs}"></script>

    <spring:url value="/client/application/js/constants/appUser.js" var="appUserJs" />
    <script src="${appUserJs}"></script>

    <spring:url value="/client/application/js/constants/events.js" var="eventsJs" />
    <script src="${eventsJs}"></script>

    <spring:url value="/client/application/js/constants/translations.js" var="translationsJs" />
    <script src="${translationsJs}"></script>

    <spring:url value="/client/application/js/constants/urls.js" var="urlsJs" />
    <script src="${urlsJs}"></script>

    <spring:url value="/client/application/js/routes.js" var="appRoutesJs" />
    <script src="${appRoutesJs}"></script>

    <spring:url value="/client/application/js/config/global.js" var="appGlobalJs" />
    <script src="${appGlobalJs}"></script>

    <spring:url value="/client/application/js/config/customJsonResult.js" var="appCustomJsonResultJs" />
    <script src="${appCustomJsonResultJs}"></script>

    <spring:url value="/client/application/js/config/exceptions.js" var="appExceptionsJs" />
    <script src="${appExceptionsJs}"></script>

    <spring:url value="/client/application/js/config/localization.js" var="localizationJs" />
    <script src="${localizationJs}"></script>

    <spring:url value="/client/application/js/controllers/HomeController.js" var="appHomeControllerJs" />
    <script src="${appHomeControllerJs}"></script>

    <spring:url value="/client/application/js/controllers/LoginController.js" var="appLoginControllerJs" />
    <script src="${appLoginControllerJs}"></script>

    <spring:url value="/client/application/js/controllers/RegisterController.js" var="appRegisterControllerJs" />
    <script src="${appRegisterControllerJs}"></script>

    <spring:url value="/client/application/js/controllers/CreateProjectController.js" var="appCreateProjectControllerJs" />
    <script src="${appCreateProjectControllerJs}"></script>

    <spring:url value="/client/application/js/controllers/UserProjectListController.js" var="appUserProjectListControllerJs" />
    <script src="${appUserProjectListControllerJs}"></script>

    <spring:url value="/client/application/js/controllers/ProjectController.js" var="appProjectControllerJs" />
    <script src="${appProjectControllerJs}"></script>

    <spring:url value="/client/application/js/directives/custom-forms.js" var="appCustomFormsDirectiveJs" />
    <script src="${appCustomFormsDirectiveJs}"></script>

    <spring:url value="/client/application/js/directives/restrict.js" var="appRestrictDirectiveJs" />
    <script src="${appRestrictDirectiveJs}"></script>

    <spring:url value="/client/application/js/directives/ks-autocomplete.js" var="appAutocompleteDirectiveJs" />
    <script src="${appAutocompleteDirectiveJs}"></script>

    <spring:url value="/client/application/js/directives/ks-navbar.js" var="appNavbarDirectiveJs" />
    <script src="${appNavbarDirectiveJs}"></script>

    <spring:url value="/client/application/js/filters/truncate.js" var="appTruncateFilterJs" />
    <script src="${appTruncateFilterJs}"></script>

    <spring:url value="/client/application/js/services/authService.js" var="appAuthServiceJs" />
    <script src="${appAuthServiceJs}"></script>

    <spring:url value="/client/application/js/services/exceptionHandlingService.js" var="appExceptionHandlingServiceJs" />
    <script src="${appExceptionHandlingServiceJs}"></script>

    <spring:url value="/client/application/js/services/imageConverter.js" var="appImageConverterServiceJs" />
    <script src="${appImageConverterServiceJs}"></script>

    <spring:url value="/client/application/js/services/permissionService.js" var="appPermissionServiceJs" />
    <script src="${appPermissionServiceJs}"></script>

    <spring:url value="/client/application/js/services/projectService.js" var="appProjectServiceJs" />
    <script src="${appProjectServiceJs}"></script>

    <spring:url value="/client/application/js/services/router.js" var="appRouterServiceJs" />
    <script src="${appRouterServiceJs}"></script>
