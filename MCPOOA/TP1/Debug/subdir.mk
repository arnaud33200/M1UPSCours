################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
O_SRCS += \
../Entree.o \
../Tableau.o \
../main.o 

CPP_SRCS += \
../Entree.cpp \
../Tableau.cpp \
../main.cpp 

OBJS += \
./Entree.o \
./Tableau.o \
./main.o 

CPP_DEPS += \
./Entree.d \
./Tableau.d \
./main.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


