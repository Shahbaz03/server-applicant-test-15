package com.mytaxi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.collect.Lists;
import com.mytaxi.constants.SwaggerConstants;
import com.mytaxi.controller.mapper.VehicleMapper;
import com.mytaxi.datatransferobject.VehicleDTO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.service.vehicle.VehicleService;
import com.mytaxi.util.LoggingInterceptor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MytaxiServerApplicantTestApplication extends WebMvcConfigurerAdapter implements CommandLineRunner {

	@Autowired
	private VehicleService vehicleService;

	public static void main(String[] args) {
		SpringApplication.run(MytaxiServerApplicantTestApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).paths(PathSelectors.any())
				.build().apiInfo(generateApiInfo())
				.securitySchemes(Lists.newArrayList(securitySchema()))
				.securityContexts(Collections.singletonList(securityContext()))
				.tags(new Tag(SwaggerConstants.BOOKING_CONTROLLER, SwaggerConstants.BOOKING_CONTROLLER_DESC),
						new Tag(SwaggerConstants.DRIVER_CONTROLLER, SwaggerConstants.DRIVER_CONTROLLER_DESC),
						new Tag(SwaggerConstants.VEHICLE_CONTROLLER, SwaggerConstants.VEHICLE_CONTROLLER_DESC),
						new Tag(SwaggerConstants.SEARCH_CONTROLLER, SwaggerConstants.SEARCH_CONTROLLER_DESC),
						new Tag(SwaggerConstants.HOME_CONTROLLER, SwaggerConstants.HOME_CONTROLLER_DESC));
	}
	private OAuth securitySchema() {
		List<AuthorizationScope> authorizationScopeList = new ArrayList<AuthorizationScope>();
		authorizationScopeList.add(new AuthorizationScope("read", "read all"));
		authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
		authorizationScopeList.add(new AuthorizationScope("write", "access all"));

		List<GrantType> grantTypes = new ArrayList<GrantType>();
		GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8080/oauth/token");

		grantTypes.add(creGrant);

		return new OAuth("oauth2schema", authorizationScopeList, grantTypes);

	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/v1/**"))
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
		authorizationScopes[2] = new AuthorizationScope("write", "write all");

		return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration("mytaxi-client", "mytaxi-secret", "", "", "", ApiKeyVehicle.QUERY_PARAM, "", " ");
	}

	private ApiInfo generateApiInfo() {
		return new ApiInfo("mytaxi Server Applicant Test Service",
				"This service is to check the technology knowledge of a server applicant for mytaxi.",
				"Version 1.0 - mw", "urn:tos", "career@mytaxi.com", "Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0");
	}

	/**
	 * adding the method to create some test data for vehicle flat file search
	 */
	@Override
	public void run(String... args) throws Exception {

		VehicleDTO vehicleDTO1 = VehicleDTO.newBuilder().setManufacturerName("BMW").setName("BMWi2").setSeatCount(4)
				.setEngineType(EngineType.DIESEL).setColor("RED").setConvertible(true).setLicensePlate("WB1001")
				.setRating(3).createVehicleDTO();

		VehicleDTO vehicleDTO2 = VehicleDTO.newBuilder().setManufacturerName("Honda").setName("Honda City")
				.setSeatCount(4).setEngineType(EngineType.DIESEL).setColor("BLUE").setConvertible(true)
				.setLicensePlate("WB1002").setRating(3).createVehicleDTO();

		VehicleDTO vehicleDTO3 = VehicleDTO.newBuilder().setManufacturerName("Honda").setName("Honda CRV")
				.setSeatCount(4).setEngineType(EngineType.PETROL).setColor("BLACK").setConvertible(false)
				.setLicensePlate("WB1003").setRating(3).createVehicleDTO();

		VehicleDTO vehicleDTO4 = VehicleDTO.newBuilder().setManufacturerName("Tesla").setName("Roadster")
				.setSeatCount(2).setEngineType(EngineType.ELECTRIC).setColor("GREEN").setConvertible(false)
				.setLicensePlate("WB1004").setRating(3).createVehicleDTO();

		VehicleDTO vehicleDTO5 = VehicleDTO.newBuilder().setManufacturerName("Hyundai").setName("i20")
				.setSeatCount(2).setEngineType(EngineType.GAS).setColor("BLACK").setConvertible(false)
				.setLicensePlate("WB1005").setRating(3).createVehicleDTO();

		VehicleDTO vehicleDTO6 = VehicleDTO.newBuilder().setManufacturerName("Honda").setName("Honda BRV")
				.setSeatCount(2).setEngineType(EngineType.ELECTRIC).setColor("White").setConvertible(true)
				.setLicensePlate("WB1006").setRating(3).createVehicleDTO();

		VehicleDTO vehicleDTO7 = VehicleDTO.newBuilder().setManufacturerName("Maruti").setName("Maruti Brezza")
				.setSeatCount(2).setEngineType(EngineType.PETROL).setColor("GREY").setConvertible(false)
				.setLicensePlate("WB1007").setRating(3).createVehicleDTO();

		vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO1));
		vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO2));
		vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO3));
		vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO4));
		vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO5));
		vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO6));
		vehicleService.create(VehicleMapper.makeVehicleDO(vehicleDTO7));


	}
}
