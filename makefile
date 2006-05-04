include makerules
SOURCE:=$(filter %.java,$(shell find .))
SOURCEBACKUP:=$(SOURCE:.java=.java~)
FILES:=$(SOURCE:.java=.class)
